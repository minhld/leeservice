package com.minhle.leeservice.kafka.avro;

import com.minhle.leeservice.model.Employee;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

@Slf4j
@NoArgsConstructor
public class AvroDeserializer implements Deserializer<Employee> {

    @Override
    public Employee deserialize(String topic, byte[] bytes) {
        Employee returnObject = null;
        try {
            if (bytes != null) {
                DatumReader<GenericRecord> datumReader = new SpecificDatumReader<>(Employee.getClassSchema());
                Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
                returnObject = (Employee) datumReader.read(null, decoder);
                log.info("deserialized data='{}'", returnObject.toString());
            }
        } catch (IOException e) {
            log.error("Unable to Deserialize bytes[] ", e);
        }
        return returnObject;
    }
}