package br.com.daniel.poc.xmlgenerator.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "product_report")
public class ProductReport {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "product")
    private List<Product> products;
}
