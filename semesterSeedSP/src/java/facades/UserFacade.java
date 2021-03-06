package facades;

import deploy.DeploymentConfiguration;
import entity.Role;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.PasswordHash;

public class UserFacade {

  EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

  public UserFacade() {
   
  }

  public User getUserByUserId(String id) {
    EntityManager em = emf.createEntityManager();
    try {
      return em.find(User.class, id);
    } finally {
      em.close();
    }
  }
  /*
   Return the Roles if users could be authenticated, otherwise null
   */
  public List<String> authenticateUser(String userName, String password) {
    EntityManager em = emf.createEntityManager();
    try {
      User user =  em.find(User.class, userName);
      if(user == null){
        return null;
      }
      try {
        boolean authenticated = PasswordHash.validatePassword(password, user.getPassword());
        return authenticated ? user.getRolesAsStrings() : null;
      } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
        Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        return null;
      }
      
    } finally {
      em.close();
    }
    
  }
public User saveUser(User u) throws NoSuchAlgorithmException, InvalidKeySpecException {
        EntityManager em = emf.createEntityManager();

        String hashedPassword = PasswordHash.createHash(u.getPassword());
        u.setPassword(hashedPassword);

        try {
            em.getTransaction().begin();
            Role role = em.find(Role.class, "User");
            u.AddRole(role);
            em.persist(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return u;
    }
}
