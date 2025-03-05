package com.growhire.site.service.imp;

import com.growhire.site.entity.Visitor;
import com.growhire.site.repository.VisitorRepository;
import com.growhire.site.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    public Visitor saveVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public long countVisitors() {
        return visitorRepository.count();
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public Visitor save(Visitor visitor) {
        // Save the visitor and return the saved entity
        return visitorRepository.save(visitor);
    }
}
