package com.teceats.backend.service;

import com.teceats.backend.model.Admin;
import com.teceats.backend.model.Approval;
import com.teceats.backend.model.Restaurant;
import com.teceats.backend.repository.AdminRepository;
import com.teceats.backend.repository.ApprovalRepository;
import com.teceats.backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * Obtener todos los administradores.
     */
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    /**
     * Aprobar un restaurante.
     */
    public Approval approveRestaurant(Long adminId, Long restaurantId, String comments) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        restaurant.setEnabled(true);
        restaurantRepository.save(restaurant);

        Approval approval = new Approval();
        approval.setAdmin(admin);
        approval.setRestaurant(restaurant);
        approval.setComments(comments);
        approval = approvalRepository.save(approval);

        return approval;
    }

    /**
     * Rechazar un restaurante.
     */
    public Approval rejectRestaurant(Long adminId, Long restaurantId, String comments) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        restaurant.setEnabled(false);
        restaurantRepository.save(restaurant);

        Approval approval = new Approval();
        approval.setAdmin(admin);
        approval.setRestaurant(restaurant);
        approval.setComments(comments);
        approval = approvalRepository.save(approval);

        return approval;
    }
}
