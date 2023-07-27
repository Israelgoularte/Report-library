package org.dev.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.dev.control.UnitControl;

public class TruncateDataBase {
    public static void truncade(){
        // Obtém uma instância do EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(UnitControl.getInstance().getUnit());
        EntityManager em = emf.createEntityManager();

        try {
            // Inicia uma transação
            em.getTransaction().begin();

            // Executa a operação de truncagem na tabela principal (user.login)
            // O cascade irá propagar a operação de truncagem para as tabelas relacionadas com cascata
            em.createNativeQuery("TRUNCATE TABLE \"user\".login CASCADE").executeUpdate();

            // Comita a transação
            em.getTransaction().commit();

            System.out.println("Truncagem realizada com sucesso no banco de dados 'user' com cascade.");
        } catch (Exception e) {
            // Se ocorrer algum erro, faça rollback na transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao realizar a truncagem no banco de dados 'user' com cascade: " + e.getMessage());
        } finally {
            // Feche o EntityManager
            em.close();
            emf.close();
        }
    }
}
