package org.dev.util;

import com.hierynomus.smbj.io.ByteChunkProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class InputStreamByteChunkProvider extends ByteChunkProvider {

    private final ByteArrayInputStream stream;

    public InputStreamByteChunkProvider(ByteArrayInputStream stream) {
        this.stream = stream;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void prepareWrite(int i) {

    }

    @Override
    public int getChunk(byte[] buffer) throws IOException {
        int available = stream.available();
        if (available <= 0) {
            return -1;
        }

        int read = stream.read(buffer, 0, Math.min(available, buffer.length));
        return read;
    }

    @Override
    public int bytesLeft() {
        return stream.available();
    }

}
