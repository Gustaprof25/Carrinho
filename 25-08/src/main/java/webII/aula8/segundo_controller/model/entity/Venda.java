/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webII.aula8.segundo_controller.model.entity;
/**
 *
 * @author Gustavo
 */

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda{

    @GeneratedValue
    @Id
    private Long id;
    private Date data;

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itemvendas;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public double total(){
        double total = 0;
        for(ItemVenda i: itemvendas){
            total += i.total();
        }
        return total;
    }
}
