package com.example.inputdemo.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class AvroReader {
    /**
     * Reads in binary Avro-encoded entities using the schema stored in the file and prints them out.
     * @param file
     * @throws IOException
     */
    public static void readAvro_v1(File file) throws IOException {
        GenericDatumReader datum = new GenericDatumReader();
        DataFileReader reader = new DataFileReader(file, datum);

        GenericData.Record record = new GenericData.Record(reader.getSchema());
        while (reader.hasNext()) {
            reader.next(record);
            System.out.format("Name:[%s]\tAddress:[%s]\tCurrency:[%s]\n", record.get("firstName")+"",record.get("address")+"",record.get("salaryCurrency")+"");
        }

        reader.close();
    }

    /**
     * Reads in binary Avro-encoded entities using a schema that is different from the writer's schema.
     * @param file
     * @throws IOException
     */
    public static void readAvro_v2(File file) throws IOException {
        GenericDatumReader datum = new GenericDatumReader(GeneralPerson.V2);
        DataFileReader reader = new DataFileReader(file, datum);

        GenericData.Record record = new GenericData.Record(GeneralPerson.V2);
        while (reader.hasNext()) {
            reader.next(record);
            System.out.format("Name:[%s]\tHome Address:[%s]\tWork Address:[%s]\n", record.get("firstName")+"",record.get("homeAddress")+"",record.get("workAddress")+"");
        }

        reader.close();
    }

}
