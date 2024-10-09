package com.minhle.leeservice.kafka.avro;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

@Slf4j
public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {
    protected final Class<T> targetType;

    public AvroDeserializer(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        T returnObject = null;
        try {
            if (bytes != null) {
                DatumReader<GenericRecord> datumReader = new SpecificDatumReader<>(targetType.newInstance().getSchema());
                Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
                returnObject = (T) datumReader.read(null, decoder);
                log.info("deserialized data='{}'", returnObject.toString());
            }
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            log.error("Unable to Deserialize bytes[] ", e);
        }
        return returnObject;
    }
}