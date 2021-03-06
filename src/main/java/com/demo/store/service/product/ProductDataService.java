package com.demo.store.service.product;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.demo.store.entity.Product;
import com.demo.store.service.DataService;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductDataService implements DataService<Product>{

    @PersistenceContext
    EntityManager em;
        

    @Override
    public Product save(Product entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Product get(Product entity) {
        return em.find(Product.class, entity.getId());
    }
    @Override
    public Product getByEntityId(Product entity) {
        log.info(" pid {}",entity.getProductId());
        return (Product) em.createQuery("FROM Product WHERE productId = :pid").setParameter("pid", entity.getProductId()).getSingleResult();
    }
    
    @Override
    public List<Product> getAll() {
        //return em.createQuery("from Product").getResultList();
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
        
    }
    @Override
    public Product remove(Product entity) {
        //if detached, attach it before it can be removed
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        return entity;
    }
    @Override
    //ugly method .... needs to relook , just support getbyname for now
    public List<Product> query(String query, Object... param) {
        return em.createNamedQuery(query, Product.class).setParameter((String)param[0], param[1]).getResultList();
        //String[] x ={"p0001","p0003"};
        //return em.createNamedQuery("Product.findByProducts", Product.class).setParameter("ids", Arrays.asList(x)).getResultList();
    }
    @Override
    public Product update(Product entity) {
        return em.merge(entity);
        
    }
}
/** 
 * //keeping ths hibernation localsessionfactory reference codes
 * 
    @Autowired
    private SessionFactory sessionFactory;

    public void test() {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
             int count = session.createSQLQuery("select * from products").list().size();
             log.info("Products - {} ", count);
            Product p = (Product)session.createQuery("from Product where name = :n").setParameter("n","Container" ).uniqueResult();
            log.info("Products query - {} ", p.getId());
            //write
            log.info("Products - new");

            p = new Product();
            p.setName("test");
            session.persist(p);
            //p.setDescription("desc");
            //p.setName("test");
            //session.saveOrUpdate(p);
            //p.setId(1L);
            //session.merge(p);
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            if (session != null) session.close();
            log.info("hbm session closed");
        }
    }
  
  
 */