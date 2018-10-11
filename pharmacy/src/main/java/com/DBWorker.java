package com;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    private String[] credentialsXML = new String[3];

    private Connection connection;

    public DBWorker()
    {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            loadFile();

            connection = DriverManager.getConnection(credentialsXML[0], credentialsXML[1], credentialsXML[2]);

            if (!connection.isClosed())
            {
                App.isConnect = "Соединение с БД установлено.";
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error");
        }
    }

    private void loadFile() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("credentials.xml");

            Node root = document.getDocumentElement();

            NodeList credentials = root.getChildNodes();

            for (int i = 0; i < credentials.getLength(); i++) {
                Node credential = credentials.item(i);
                if (credential.getNodeType() != Node.TEXT_NODE) {
                    NodeList credentialProps = credential.getChildNodes();
                    int count = 0;
                    for (int j = 0; j < credentialProps.getLength(); j++) {
                        Node credentialProp = credentialProps.item(j);

                        if (credentialProp.getNodeType() != Node.TEXT_NODE) {
                            count++;
                            credentialsXML[count-1] = credentialProp.getChildNodes().item(0).getTextContent();
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
