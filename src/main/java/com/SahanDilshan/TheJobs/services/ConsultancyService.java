package com.SahanDilshan.TheJobs.services;

import com.SahanDilshan.TheJobs.models.ApplicationUser;
import com.SahanDilshan.TheJobs.models.Consultancy;
import com.SahanDilshan.TheJobs.models.ConsultancyDTO;
import com.SahanDilshan.TheJobs.repository.ConsultancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultancyService {

    @Autowired
    private ConsultancyRepository consultancyRepository;

    public Consultancy createConsultancy(ConsultancyDTO consultancyDTO) {
        Integer userId = consultancyDTO.getUserId();
        if (consultancyRepository.existsById(userId)) {
            throw new IllegalStateException("A record already exists for you!!!.");
        }

        Consultancy consultancy = new Consultancy();
        consultancy.setName(consultancyDTO.getName());
        consultancy.setCountry(consultancyDTO.getCountry());
        consultancy.setGender(consultancyDTO.getGender());
        consultancy.setDescription(consultancyDTO.getDescription());
        consultancy.setPhone(consultancyDTO.getPhone());
        consultancy.setEmail(consultancyDTO.getEmail());
        consultancy.setAvailability(consultancyDTO.getAvailability());
        consultancy.setUserId(consultancyDTO.getUserId());

        return consultancyRepository.save(consultancy);
    }

    public Consultancy updateConsultancy(Integer consultancyId, ConsultancyDTO consultancyDTO) {
        Consultancy existingConsultancy = consultancyRepository.findById(consultancyId)
                .orElseThrow(() -> new IllegalStateException("Can't found record!!!"));

        existingConsultancy.setName(consultancyDTO.getName());
        existingConsultancy.setCountry(consultancyDTO.getCountry());
        existingConsultancy.setGender(consultancyDTO.getGender());
        existingConsultancy.setDescription(consultancyDTO.getDescription());
        existingConsultancy.setPhone(consultancyDTO.getPhone());
        existingConsultancy.setEmail(consultancyDTO.getEmail());
        existingConsultancy.setAvailability(consultancyDTO.getAvailability());
        existingConsultancy.setUserId(consultancyDTO.getUserId());

        return consultancyRepository.save(existingConsultancy);
    }

    public Consultancy getConsultancyByUserId(Integer userId) {
        return consultancyRepository.findByUserId(userId);
    }

    public String  deleteConsultancyByUserId(Integer userId) {
        Optional<Consultancy> existingConsultancy = Optional.ofNullable(consultancyRepository.findByUserId(userId));
        if (existingConsultancy.isPresent()) {
            consultancyRepository.delete(existingConsultancy.get());
            return "Deleted";
        } else {
            return "Not found";
        }
    }


}
