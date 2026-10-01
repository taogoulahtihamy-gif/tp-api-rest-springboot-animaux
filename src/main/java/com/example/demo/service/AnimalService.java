package com.example.demo.service;

import com.example.demo.model.Animal;
import com.example.demo.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // Récupérer tous les animaux
    public List<Animal> getAllAnimaux() {
        return animalRepository.findAll();
    }

    // Récupérer un animal par son id
    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    // Ajouter un animal
    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    // Modifier un animal
    public Optional<Animal> updateAnimal(Long id, Animal animal) {

        return animalRepository.findById(id)
                .map(animalExistant -> {

                    animalExistant.setNom(animal.getNom());
                    animalExistant.setCategorie(animal.getCategorie());
                    animalExistant.setStatus(animal.getStatus());

                    return animalRepository.save(animalExistant);
                });
    }

    // Supprimer un animal
    public boolean deleteAnimal(Long id) {

        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return true;
        }

        return false;
    }
}