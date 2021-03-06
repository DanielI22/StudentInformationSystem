package xml_parser_utils;

import bg.tu_varna.sit.StudentServiceSystem;
import exceptions.InvalidFileOrFilePathException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class JaxXMLToObject {
    public static StudentServiceSystem jaxbXmlFileToObject(String fileName) throws InvalidFileOrFilePathException {
        try {
            File xmlFile = new File(fileName);
            if(xmlFile.createNewFile()) {
                System.out.println("Successfully created " + fileName);
                Simulator.simulate();
                return StudentServiceSystem.getInstance();
            }
            else {
                JAXBContext jaxbContext;

                jaxbContext = JAXBContext.newInstance(StudentServiceSystem.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                JAXBElement<StudentServiceSystem> root =
                        jaxbUnmarshaller.unmarshal(new StreamSource(xmlFile), StudentServiceSystem.class);
                StudentServiceSystem mySystem = root.getValue();
                return mySystem;
            }
        } catch (Exception e) {
            throw new InvalidFileOrFilePathException();
        }
    }
}
