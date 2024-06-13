package com.target.shipments.util;

import com.target.shipments.entity.Package;
import com.target.shipments.entity.Shipment;
import com.target.shipments.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("all")
public class ShipmentParserTest {

    @Test
    public void testParseShipment_ValidData() {
        String shipmentData = """
                {
                  "shipment_id": "SH512391234",
                  "sender": {
                    "name": "Brad Pit",
                    "address": "754 Maple Street, Berlin, Germany",
                    "contact": "brad.pit@gmail.com"
                  },
                  "recipient": {
                    "name": "Joshua",
                    "address": "465 hellen Street, Toronto, Canada",
                    "contact": "josh.joshua@gmail.com"
                  },
                  "package_details": [
                    {
                      "package_id": "PKG004",
                      "description": "Books",
                      "weight_kg": 10.8,
                      "dimensions_cm": {
                        "length": 20,
                        "width": 5,
                        "height": 6
                      }
                    },
                    {
                      "package_id": "PKG005",
                      "description": "Electrical",
                      "weight_kg": 8.6,
                      "dimensions_cm": {
                        "length": 32,
                        "width": 15,
                        "height": 12
                      }
                    },
                    {
                      "package_id": "PKG006",
                      "description": "Clothing",
                      "weight_kg": 26.5,
                      "dimensions_cm": {
                        "length": 16,
                        "width": 30,
                        "height": 20
                      }
                    }
                  ],
                  "shipping_method": "Express",
                  "estimated_delivery_date": "2024-07-02",
                  "total_cost": 60.00,
                  "currency": "INR",
                  "status": "In transit"
                }""";

        ShipmentParser shipmentParser = new ShipmentParser();
        Object[] result = shipmentParser.parseShipment(shipmentData);
        Shipment shipment = (Shipment) result[0];
        User[] users = (User[]) result[1];
        Set<Package> packages = (Set<Package>) result[2];

        assertNotNull(shipment);
        assertEquals("SH512391234", shipment.getId());
        assertNotNull(users);
        assertEquals(2, users.length);
        assertEquals("Brad Pit", users[0].getName());
        assertEquals("Joshua", users[1].getName());
        assertNotNull(packages);
        assertEquals(3, packages.size());
    }

    @Test
    public void testParseShipment_InvalidJson() {
        String invalidJson = "{\n" +
                "  \"shipment_id\": \"SH512391234\",\n" +
                "  \"sender\": {\n" +
                "    \"name\": \"Brad Pit\",\n" +
                "    \"address\": \"754 Maple Street, Berlin, Germany\",\n" +
                "    \"contact\": \"brad.pit@gmail.com\"\n" +
                "  },\n" +
                "  \"recipient\": {\n" +
                "    \"name\": \"Joshua\",\n" +
                "    \"address\": \"465 hellen Street, Toronto, Canada\",\n" +
                "    \"contact\": \"josh.joshua@gmail.com\"\n" +
                "  },\n" +
                "  \"package_details\": [\n" +
                "    {\n" +
                "      \"package_id\": \"PKG004\",\n" +
                "      \"description\": \"Books\",\n" +
                "      \"weight_kg\": 10.8,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 20,\n" +
                "        \"width\": 5,\n" +
                "        \"height\": 6\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"shipping_method\": \"Express\",\n" +
                "  \"estimated_delivery_date\": \"2024-07-02\",\n" +
                "  \"total_cost\": 60.00,\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"status\": \"In transit\"\n";

        ShipmentParser shipmentParser = new ShipmentParser();
        assertThrows(IllegalArgumentException.class, () -> shipmentParser.parseShipment(invalidJson));
    }

    @Test
    public void testParseShipment_MissingMandatoryField() {
        String invalidJson = """
                {
                  "shipment_id": "SH512391234",
                  "sender": {
                    "name": "Brad Pit",
                    "address": "754 Maple Street, Berlin, Germany",
                    "contact": "brad.pit@gmail.com"
                  },
                  "package_details": [
                    {
                      "package_id": "PKG004",
                      "description": "Books",
                      "weight_kg": 10.8,
                      "dimensions_cm": {
                        "length": 20,
                        "width": 5,
                        "height": 6
                      }
                    }
                  ],
                  "shipping_method": "Express",
                  "estimated_delivery_date": "2024-07-02",
                  "total_cost": 60.00,
                  "currency": "INR",
                  "status": "In transit"
                }""";

        ShipmentParser shipmentParser = new ShipmentParser();
        assertThrows(IllegalArgumentException.class, () -> shipmentParser.parseShipment(invalidJson));
    }

    @Test
    public void testParseShipment_InvalidCurrency() {
        String invalidJson = """
                {
                  "shipment_id": "SH512391234",
                  "sender": {
                    "name": "Brad Pit",
                    "address": "754 Maple Street, Berlin, Germany",
                    "contact": "brad.pit@gmail.com"
                  },
                  "recipient": {
                    "name": "Joshua",
                    "address": "465 hellen Street, Toronto, Canada",
                    "contact": "josh.joshua@gmail.com"
                  },
                  "package_details": [
                    {
                      "package_id": "PKG004",
                      "description": "Books",
                      "weight_kg": 10.8,
                      "dimensions_cm": {
                        "length": 20,
                        "width": 5,
                        "height": 6
                      }
                    }
                  ],
                  "shipping_method": "Express",
                  "estimated_delivery_date": "2024-07-02",
                  "total_cost": 60.00,
                  "currency": "USD", // Invalid currency code
                  "status": "In transit"
                }""";

        ShipmentParser shipmentParser = new ShipmentParser();
        assertThrows(IllegalArgumentException.class, () -> shipmentParser.parseShipment(invalidJson));
    }

    @Test
    public void testParseShipment_EmptyPackageDetails() {
        String invalidJson = """
                {
                  "shipment_id": "SH512391234",
                  "sender": {
                    "name": "Brad Pit",
                    "address": "754 Maple Street, Berlin, Germany",
                    "contact": "brad.pit@gmail.com"
                  },
                  "recipient": {
                    "name": "Joshua",
                    "address": "465 hellen Street, Toronto, Canada",
                    "contact": "josh.joshua@gmail.com"
                  },
                  "package_details": [], // Empty package details
                  "shipping_method": "Express",
                  "estimated_delivery_date": "2024-07-02",
                  "total_cost": 60.00,
                  "currency": "INR",
                  "status": "In transit"
                }""";

        ShipmentParser shipmentParser = new ShipmentParser();
        assertThrows(IllegalArgumentException.class, () -> shipmentParser.parseShipment(invalidJson));
    }

    @Test
    public void testParseShipment_InvalidPackageWeight() {
        String invalidJson = """
                {
                  "shipment_id": "SH512391234",
                  "sender": {
                    "name": "Brad Pit",
                    "address": "754 Maple Street, Berlin, Germany",
                    "contact": "brad.pit@gmail.com"
                  },
                  "recipient": {
                    "name": "Joshua",
                    "address": "465 hellen Street, Toronto, Canada",
                    "contact": "josh.joshua@gmail.com"
                  },
                  "package_details": [
                    {
                      "package_id": "PKG004",
                      "description": "Books",
                      "weight_kg": -5.0, // Invalid negative weight
                      "dimensions_cm": {
                        "length": 20,
                        "width": 5,
                        "height": 6
                      }
                    }
                  ],
                  "shipping_method": "Express",
                  "estimated_delivery_date": "2024-07-02",
                  "total_cost": 60.00,
                  "currency": "INR",
                  "status": "In transit"
                }""";

        ShipmentParser shipmentParser = new ShipmentParser();
        assertThrows(IllegalArgumentException.class, () -> shipmentParser.parseShipment(invalidJson));
    }
}


