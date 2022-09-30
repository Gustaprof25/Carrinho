package webII.aula8.segundo_controller.model.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import webII.aula8.segundo_controller.model.entity.Produto;
import webII.aula8.segundo_controller.model.entity.Venda;

@Repository
public class VendaRepository {

    
    @PersistenceContext
    private EntityManager em;

        public void save(Venda venda){
        em.persist(venda);
    }

    public Venda venda(Long id){
        return em.find(Venda.class, id);
    }

    public List<Venda> Vendas(){
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }

    public void remove(Long id){
        Venda p = em.find(Venda.class, id);
        em.remove(p);
    }

    public void update(Venda venda){
        em.merge(venda);
    }

}
