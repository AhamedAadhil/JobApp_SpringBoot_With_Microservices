package com.aathil.companyms.company.impl;


import com.aathil.companyms.company.Company;
import com.aathil.companyms.company.CompanyRepository;
import com.aathil.companyms.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
      return  companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company,Long id) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company1= companyOptional.get();
            company1.setDescription(company.getDescription());
            company1.setName(company.getName());
            companyRepository.save(company1);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany( Long id) {
      Optional<Company> companyOptional = companyRepository.findById(id);
      if(companyOptional.isPresent()){
          companyRepository.deleteById(id);
          return  true;
      }
      return  false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
