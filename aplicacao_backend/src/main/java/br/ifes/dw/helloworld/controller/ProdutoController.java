package br.ifes.dw.helloworld.controller;

import br.ifes.dw.helloworld.dto.ProdutoInputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import br.ifes.dw.helloworld.model.Produto;
import br.ifes.dw.helloworld.application.AppProduto;
import org.springframework.beans.factory.annotation.*;
import br.ifes.dw.helloworld.exception.*;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private AppProduto appProduto;

    @GetMapping("/")
    public List<Produto> getAllProdutos() {
        return appProduto.getAllProdutos().getBody();
    }

    @PostMapping("/")
    public Produto saveProduto(@RequestBody ProdutoInputDTO produto) {
        return appProduto.saveProduto(produto).getBody();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        appProduto.deleteProduct(id);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody ProdutoInputDTO produto) {
        appProduto.updateProduct(id,produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable int id) {

        try {

            return appProduto.getOneProduct(id);

        } catch (Exception e) {

            // erro desconhecido
            return null;
        }

    }

}