package lotus.auth.service;

import lotus.auth.models.Policy;
import lotus.auth.models.Role;
import lotus.auth.repositiries.PolicyRepository;
import lotus.auth.repositiries.RoleRepository;
import lotus.auth.repositiries.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by flamen on 16-9-18.
 */
@Service("auth.policyService")
public class PolicyService {
    public boolean can(long userId, String roleName) {
        return can(userId, roleName, null, null);
    }

    public <T> boolean can(long userId, String roleName, Class<T> resourceType) {
        return can(userId, roleName, resourceType, null);
    }

    public <T> boolean can(long userId, String roleName, Class<T> resourceType, Long resourceId) {
        Role role = getRole(roleName, resourceType, resourceId);
        Policy policy = policyRepository.findByUserIdAndRoleId(userId, role.getId());
        Date now = new Date();
        return policy != null && now.after(policy.getStartUp()) && now.before(policy.getShutDown());
    }

    public void allow(long userId, String roleName) {
        allow(userId, roleName, null, null);
    }

    public <T> void allow(long userId, String roleName, Class<T> resourceType) {
        allow(userId, roleName, resourceType, null);

    }

    public <T> void allow(long userId, String roleName, Class<T> resourceType, Long resourceId) {
        LocalDateTime now = LocalDateTime.now();
        allow(userId, roleName, resourceType, resourceId,
                Date.from(now.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(now.plusYears(10).atZone(ZoneId.systemDefault()).toInstant())
        );

    }

    public <T> void allow(long userId, String roleName, Class<T> resourceType, Long resourceId, Date startUp, Date shutDown) {
        Role role = getRole(roleName, resourceType, resourceId);
        Policy policy = policyRepository.findByUserIdAndRoleId(userId, role.getId());
        if (policy == null) {
            policy.setUser(userRepository.findOne(userId));
            policy.setRole(role);
        }
        policy.setShutDown(shutDown);
        policy.setStartUp(startUp);
        policyRepository.save(policy);
    }

    public void deny(long userId, String roleName) {
        deny(userId, roleName, null, null);
    }

    public <T> void deny(long userId, String roleName, Class<T> resourceType) {
        deny(userId, roleName, resourceType, null);
    }

    public <T> void deny(long userId, String roleName, Class<T> resourceType, Long resourceId) {
        Role role = getRole(roleName, resourceType, resourceId);
        Policy policy = policyRepository.findByUserIdAndRoleId(userId, role.getId());
        if (policy != null) {
            policyRepository.delete(policy);
        }
    }

    private <T> Role getRole(String roleName, Class<T> resourceType, Long resourceId) {
        String rty = this.resourceType(resourceType);
        logger.debug(rty);
        Role role = roleRepository.findByNameAndResourceTypeAndResourceId(roleName, rty, resourceId);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            role.setResourceId(resourceId);
            role.setResourceType(rty);
            roleRepository.save(role);
        }
        return role;
    }

    private <T> String resourceType(Class<T> clazz) {
        return clazz == null ? null : clazz.getCanonicalName();
    }

    @Resource
    RoleRepository roleRepository;
    @Resource
    PolicyRepository policyRepository;
    @Resource
    UserRepository userRepository;
    private final static Logger logger = LoggerFactory.getLogger(PolicyService.class);
}
