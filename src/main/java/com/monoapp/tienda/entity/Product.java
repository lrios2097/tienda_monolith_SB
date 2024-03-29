package com.monoapp.tienda.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

//import jakarta.persistence.*; // No utilizar jakarta, utilizar javax de maven

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Where(clause = "state = true") // Eliminacion Logica
public class Product {

    @Id //le indico a Hibernate que va a ser mi llave primaria
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para que el id se genere
    private Long idProduct;


    private String name;

    private Double price;

    @Column(name = "created_at") //Asi estara en la DB
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt; //Importo Date de Java util

    private Boolean state;
    /*
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;*/

    @Column(name = "category_id")
    private Long categoryId;

}
