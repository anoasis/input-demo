package com.example.inputdemo.avro;

import com.example.inputdemo.model.Person;
import com.example.inputdemo.model.Person_v2;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

import java.io.*;
import java.util.Date;

public class AvroWriter {


    /**
     * Writes out Java objects into a binary Avro-encoded file
     * @param file where to store serialized Avro records
     * @param people is an array of objects to be serialized
     * @throws IOException
     */
    public static void avroWrite_v1(File file, GeneralPerson[] people) throws IOException {
        GenericDatumWriter datum = new GenericDatumWriter(GeneralPerson.V1);
        DataFileWriter writer = new DataFileWriter(datum);

        writer.setMeta("Version", "v1");
        writer.setMeta("Date", new Date().toString());

        writer.create(GeneralPerson.V1, file);
        for (GeneralPerson pg : people){
            writer.append(pg.serialize_v1(pg));
        }

        writer.close();
    }

    public static void avroWrite_v1(File file, Person[] people) throws IOException {
        GenericDatumWriter datum = new GenericDatumWriter(GeneralPerson.V1);
        DataFileWriter writer = new DataFileWriter(datum);

        writer.setMeta("Version", "v1");
        writer.setMeta("Date", new Date().toString());

        writer.create(GeneralPerson.V1, file);
        for (Person p : people){
            GeneralPerson pg = GeneralPerson.generalize_v1(p);
            writer.append(pg.serialize_v1(pg));
        }

        writer.close();
    }

    public static void avroWrite_v2(File file, GeneralPerson[] people) throws IOException {
        GenericDatumWriter datum = new GenericDatumWriter(GeneralPerson.V2);
        DataFileWriter writer = new DataFileWriter(datum);

        writer.setMeta("Version", "v2");
        writer.setMeta("Date", new Date().toString());

        writer.create(GeneralPerson.V2, file);
        for (GeneralPerson pg : people){
            writer.append(pg.serialize_v2(pg));
        }

        writer.close();
    }
    public static void avroWrite_v2(File file, Person_v2[] people) throws IOException {
        GenericDatumWriter datum = new GenericDatumWriter(GeneralPerson.V2);
        DataFileWriter writer = new DataFileWriter(datum);

        writer.setMeta("Version", "v2");
        writer.setMeta("Date", new Date().toString());

        writer.create(GeneralPerson.V2, file);
        for (Person_v2 p : people){
            GeneralPerson pg = GeneralPerson.generalize_v2(p);
            writer.append(pg.serialize_v2(pg));
        }

        writer.close();
    }

    /**
     * Writes out Java objects into a JSON-encoded file
     * @param file where to store serialized Avro records
     * @param people people is an array of objects to be serialized
     * @throws IOException
     */
    public static void jsonWrite_v1(File file, GeneralPerson[] people) throws IOException {
        GenericDatumWriter writer = new GenericDatumWriter(GeneralPerson.V1);
        Encoder e = EncoderFactory.get().jsonEncoder(GeneralPerson.V1, new FileOutputStream(file));

        for (GeneralPerson p : people)
            writer.write(p.serialize_v1(p), e);

        e.flush();
    }
    public static void jsonWrite_v2(File file, GeneralPerson[] people) throws IOException {
        GenericDatumWriter writer = new GenericDatumWriter(GeneralPerson.V2);
        Encoder e = EncoderFactory.get().jsonEncoder(GeneralPerson.V2, new FileOutputStream(file));

        for (GeneralPerson p : people)
            writer.write(p.serialize_v2(p), e);

        e.flush();
    }
    public static String json_v1(GeneralPerson gp) throws IOException {
        GenericDatumWriter writer = new GenericDatumWriter(GeneralPerson.V1);
        OutputStream out = new ByteArrayOutputStream();
        Encoder e = EncoderFactory.get().jsonEncoder(GeneralPerson.V1, out);

        writer.write(gp.serialize_v1(gp), e);
        e.flush();
        return out.toString();
    }
    public static String json_v2(GeneralPerson gp) throws IOException {
        GenericDatumWriter writer = new GenericDatumWriter(GeneralPerson.V2);
        OutputStream out = new ByteArrayOutputStream();
        Encoder e = EncoderFactory.get().jsonEncoder(GeneralPerson.V2, out);

        writer.write(gp.serialize_v2(gp), e);
        e.flush();
        return out.toString();
    }
}
