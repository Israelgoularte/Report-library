package org.python_server;

import javax.xml.bind.JAXB;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    private static final String CONFIG_FILE = "config.xml";

    public static void main(String[] args) throws IOException {
        ServerInfo serverInfo = loadServerInfo();

        String serverIp = serverInfo.getServerIp();
        int serverPort = serverInfo.getServerPort();

        if (args.length > 0) {
            Socket socket = new Socket(serverIp, serverPort);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            String[] commandos = args[0].split(",");

            System.out.println(args[0]);
            // Send command to server
            switch (commandos[0]) {
                case "exibir":
                    exibir(out,in);
                    break;
                case "executar":
                    executar(commandos[1], out,in);
                    break;
                case "salvar":
                    salvar(commandos[1], out);
                    Client.main(new String[] {"exibir"});
                    break;
                default:
                    System.out.println("comando invalido!");
                    socket.close();
                    System.exit(1);
            }


            socket.close();
        } else {
            System.out.println("No Arqs");
        }
    }

    public static void exibir(OutputStream out,InputStream in) throws IOException {
        out.write(("2").getBytes(StandardCharsets.UTF_8));
        out.flush();
        respostaServidor(in);

    }

    public static void salvar(String directory, OutputStream out) throws IOException {
        out.write("1".getBytes(StandardCharsets.UTF_8));
        out.flush();

        File file = new File(directory);
        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));

        out.write((file.getName() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
        out.flush();

        DataOutputStream dataOutputStream = new DataOutputStream(out);


        byte[] buffer = new byte[24];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytesRead);
        }
        dataOutputStream.close();
        inputStream.close();
    }

    public static void executar(String programa, OutputStream out,InputStream in) throws IOException {
        out.write(("3" .trim()).getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.write((programa ).getBytes(StandardCharsets.UTF_8));
        out.flush();
        respostaServidor(in);
    }

    public static void respostaServidor(InputStream in) throws IOException {
         //Wait for response from server
        System.out.println("resposta do servidor");
        byte[] buffer = new byte[1024];
        String response = "";
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            response += new String(buffer, 0, bytesRead);
            }

         //Print response from server
        System.out.println(response);
    }

    private static ServerInfo loadServerInfo() {
        File configFile = new File(CONFIG_FILE);
        if (configFile.exists()) {
            return JAXB.unmarshal(configFile, ServerInfo.class);
        } else {
            // Se o arquivo não existir, retorna valores padrão
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setServerIp("localhost");
            serverInfo.setServerPort(8080);
            return serverInfo;
        }
    }

    private static void saveServerInfo(ServerInfo serverInfo) {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            JAXB.marshal(serverInfo, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
