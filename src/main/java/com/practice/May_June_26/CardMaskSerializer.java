//package com.practice.May_June_26;
//
//import tools.jackson.core.JsonGenerator;
//
//import java.io.IOException;
//
//public class CardMaskSerializer extends JsonSerializer<String> {
//    @Override
//    public void serialize(String value, JsonGenerator gen,
//                         SerializerProvider sp) throws IOException {
//        if (value != null && value.length() >= 4) {
//            // Show only last 4 digits: ****-****-****-1234
//            String masked = '*'.repeat(value.length() - 4)
//                           + value.substring(value.length() - 4);
//            gen.writeString(masked);
//        } else {
//            gen.writeString('****');
//        }
//    }
//}
