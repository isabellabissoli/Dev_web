package br.ifes.dw.helloworld.model;

//import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Data;


@Entity
@Table(name= "produtos")
@Data
//@Table(name = "foods")
//@Entity(name = "foods")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nome;

  private double preco;

}
