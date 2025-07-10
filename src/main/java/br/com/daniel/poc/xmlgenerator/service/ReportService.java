package br.com.daniel.poc.xmlgenerator.service;

import br.com.daniel.poc.xmlgenerator.model.Product;
import br.com.daniel.poc.xmlgenerator.model.ProductReport;
import br.com.daniel.poc.xmlgenerator.repository.ProductRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
public class ReportService {

    private final ProductRepository productRepository;
    private final XmlMapper xmlMapper;
    private final Path caminhoDoRelatorio;

    public ReportService(ProductRepository productRepository,
                         @Value("${report.storage.directory:reports/xml}") String storageDirectory) {
        this.productRepository = productRepository;
        this.xmlMapper = new XmlMapper();
        this.xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        this.caminhoDoRelatorio = Paths.get(storageDirectory);
    }

    public String generateProductReport() throws IOException {
        log.info("Iniciando geração de relatório de produtos...");

        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            log.warn("Nenhum produto encontrado no banco de dados.");
            throw new IllegalStateException("Nenhum produto encontrado para gerar o relatório.");
        }
        log.info("{} produtos encontrados para inclusão no relatório.", products.size());

        ProductReport reportData = new ProductReport(products);
        String xmlContent = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reportData);

        Files.createDirectories(caminhoDoRelatorio);

        Path targetPath = createUniqueFilePath();
        Files.writeString(targetPath, xmlContent);

        log.info("Relatório salvo com sucesso em: {}", targetPath.toAbsolutePath());
        return targetPath.toAbsolutePath().toString();
    }

    /**
     * Gera um caminho de arquivo único para evitar sobrescrita.
     *
     * @return Um objeto Path representando o caminho completo do novo arquivo.
     */
    private Path createUniqueFilePath() {
        final String baseName = "product_report";
        final String extension = ".xml";

        Path filePath = this.caminhoDoRelatorio.resolve(baseName + extension); // Aqui verifica se existe o arquivo com o nome que será salvo.

        int counter = 1;
        while (Files.exists(filePath)) {
            String newFileName = baseName + "(" + counter + ")" + extension;
            filePath = this.caminhoDoRelatorio.resolve(newFileName);
            counter++;
        }

        log.info("Caminho de arquivo definido como: {}", filePath);
        return filePath;
    }

}
