package gte.br.gte3.Services;

import gte.br.gte3.Model.Tarefa;
import gte.br.gte3.Util.HibernateUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    public static List<Tarefa> listaTarefa(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        TypedQuery<Tarefa> query = session.createQuery("from Tarefa ", Tarefa.class);
        return query.getResultList();
    }

    public void salvarTarefa(Tarefa tarefa){
        Transaction transaction = session.beginTransaction();
        session.persist(tarefa);
        transaction.commit();
    }

    public List<Tarefa> getConsultInterval() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Tarefa> query = session.createQuery("from Tarefa ", Tarefa.class);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

}
