package br.ifes.dw.helloworld.application;

import br.ifes.dw.helloworld.dto.ProdutoInputDTO;
import br.ifes.dw.helloworld.model.Produto;
import br.ifes.dw.helloworld.repositories.ProdutoRepositorio;


import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
public class AppProduto {

  @Autowired
  ProdutoRepositorio repositorioProduto;



  //@PostMapping("/produtos")
  public ResponseEntity<Produto> saveProduto(@RequestBody @Valid ProdutoInputDTO productRecordDto) { //@Valid faz a validação
    var produto = new Produto();
    BeanUtils.copyProperties(productRecordDto, produto); //O beanUtil converte o dto no model
    return ResponseEntity.status(HttpStatus.CREATED).body(repositorioProduto.save(produto)); //O status é 201 junto com o body -- o Repositorio já possui os métodos JPAs salvos
  }

  //@GetMapping("/produtos")
  public ResponseEntity<List<Produto>> getAllProdutos(){
    return ResponseEntity.status(HttpStatus.OK).body(repositorioProduto.findAll());
  }

  //@GetMapping("/produtos/{id}")
  public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") int id){ //O get One utiliza do getMapping -- o responseEntity representa toda resposta do HTTP e aqui será um objeto
    Optional<Produto> produtos = repositorioProduto.findById(id); //Optional é um método para trabalhar com já prontos
    if(produtos.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe");
    }
    return ResponseEntity.status(HttpStatus.OK).body(produtos.get());
  }

  //@PutMapping("/produtos/{id}")
  public ResponseEntity<Object> updateProduct(@PathVariable(value="id") int id,
                                              @RequestBody @Valid ProdutoInputDTO productRecordDto) {
    Optional<Produto> produtos = repositorioProduto.findById(id);
    if(produtos.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado"); //Não encontra e retorna o HttpStatus
    }
    var produto = produtos.get(); //Aqui ele pega o produto pelo id e atriibui ele a uma variavel
    BeanUtils.copyProperties(productRecordDto, produto); //Converterndo o dto em model
    return ResponseEntity.status(HttpStatus.OK).body(repositorioProduto.save(produto));
  }

  @DeleteMapping("/produtos/{id}")
  public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") int id) {
    Optional<Produto> produtos = repositorioProduto.findById(id);
    if(produtos.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
    }
    repositorioProduto.delete(produtos.get()); //O jpa já possui o delete
    return ResponseEntity.status(HttpStatus.OK).body("Produto deletado :)");
  }


}








//  private List<Produto> produtos = new ArrayList<Produto>();
//  private int lastId = 0;
//
//  public List<Produto> getAll() throws FileNotFoundException {
//    try{
//      carregarArq();
//    } catch (FileNotFoundException e){
//      this.salvarArq();
//    }
//    return produtos;
//  }
//
//  public Produto create(ProdutoInputDTO produtoInputDTO) throws FileNotFoundException {
//    lastId++;
//    Produto produto = new Produto();
//    produto.setId(lastId);
//    produto.setNome(produtoInputDTO.getNome());
//    produto.setPreco(produtoInputDTO.getPreco());
//    produtos.add(produto);
//    this.salvarArq();
//
//    return produto;
//  }
//
//  public void delete(int id) {
//    this.produtos.removeIf(produto -> produto.getId() == id);
//  }
//
////  public void update(int id){
////
////    }
////  }
//
//  public Produto getById(int id) throws NotFoundException, VaiMeuFilhoException {
//
//    if (id == 0) {
//      throw new VaiMeuFilhoException();
//    }
//
//    for (Produto produto : this.produtos) {
//      if (produto.getId() == id) {
//        return produto;
//      }
//    }
//    throw new NotFoundException();
//
//  }
//
//  public void salvarArq() throws FileNotFoundException{
//    try {
//      FileWriter f = new FileWriter("produtos.txt");
//      BufferedWriter buff = new BufferedWriter(f);
//
//      for (Produto produto : this.produtos) {
//        buff.write("id: " + Integer.toString(produto.getId()) + '\n');
//        buff.write("nome: " + produto.getNome() + '\n');
//        buff.write("Preço: " + Double.toString(produto.getPreco()) + '\n');
//      }
//
//      buff.close();
//    } catch (IOException e) {
//      System.out.println("Erro ao Salvar");
//
//    }
//
//  }
//
//  public void carregarArq() throws FileNotFoundException {
//    try {
//      FileReader f = new FileReader("produtos");
//      BufferedReader buff = new BufferedReader(f);
//
//      int leituraString = Integer.parseInt(buff.readLine());
//
//      buff.close();
//    }catch (IOException e) {
//      System.out.println("ERRO AO CARREGAR ARQUIVO. Outro irá ser criado...");
//      this.salvarArq();
//    } catch (NumberFormatException e) {
//      System.out.println("Não existe produtos");
//    }
//
//
//
//
//  }
//
//}