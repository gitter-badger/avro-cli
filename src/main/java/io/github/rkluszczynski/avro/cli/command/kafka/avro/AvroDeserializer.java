package io.github.rkluszczynski.avro.cli.command.kafka.avro;

import java.nio.ByteBuffer;

final class AvroDeserializer {

    private static final byte MAGIC_BYTE = 0x0;

    private static final int HEADER_SIZE = Byte.BYTES + Integer.BYTES; // magic byte + schema version

    private final byte[] payload;
    private final int writerSchemaVersion;
    private final int avroBinaryOffset;
    private final int avroBinaryLength;

    public AvroDeserializer(byte[] payload) {
        this.payload = payload;

        this.writerSchemaVersion = parseWriterSchemaVersion(payload);
        this.avroBinaryOffset = HEADER_SIZE;
        this.avroBinaryLength = payload.length - (HEADER_SIZE);
    }

    private int parseWriterSchemaVersion(byte[] payload) {
        ByteBuffer buffer = ByteBuffer.wrap(payload);

        byte magicByte = buffer.get();
        if (magicByte != MAGIC_BYTE) {
            throw new RuntimeException(String.format("Could not deserialize payload, unknown magic byte: '%s'.", magicByte));
        }

        return buffer.getInt();
    }

    public byte[] getPayload() {
        return payload;
    }

    public int getWriterSchemaVersion() {
        return writerSchemaVersion;
    }

    public int getAvroBinaryOffset() {
        return avroBinaryOffset;
    }

    public int getAvroBinaryLength() {
        return avroBinaryLength;
    }


}