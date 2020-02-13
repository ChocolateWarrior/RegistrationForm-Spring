package helvetica.application.services;

import helvetica.application.dtos.RequestDTO;
import helvetica.application.entities.RepairRequest;
import helvetica.application.entities.RequestState;
import helvetica.application.entities.User;
import helvetica.application.repositories.RequestRepository;
import helvetica.application.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository=userRepository;
    }

    public void pushRequest(RequestDTO dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        RepairRequest requestToAdd = RepairRequest.builder()
                .user(user)
                .specification(dto.getSpecification())
                .description(dto.getDescription())
                .requestTime(LocalDateTime.now())
                .build();

        requestRepository.save(requestToAdd);
    }

    public List<RepairRequest> getAllRequests(){
        return new ArrayList<>(requestRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    public RepairRequest getRequestById(int requestId){
        return requestRepository.findById(requestId);
    }

    public void addRequestMaster(int requestId, User master){
        RepairRequest request = getRequestById(requestId);
        request.addMaster(master);
        requestRepository.save(request);
    }

    public void setRequestComplete(int requestId){
        RepairRequest request = getRequestById(requestId);
        request.setState(RequestState.COMPLETED);
        request.setFinishTime(LocalDateTime.now());
        requestRepository.save(request);
    }

    public void setRequestPrice(int requestId, BigDecimal price){
        RepairRequest request = getRequestById(requestId);
        request.setPrice(price);
        request.setState(RequestState.ACCEPTED);
        requestRepository.save(request);
    }

    public void setRequestRejection(int requestId, String message){
        RepairRequest request = getRequestById(requestId);
        request.setState(RequestState.REJECTED);
        request.setRejectionMessage(message);
        requestRepository.save(request);
    }

    public List<RepairRequest> getRequestsByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return requestRepository.findByUser(user);
    }

    public List<RepairRequest> getRequestsByMaster(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return requestRepository.findByMasters(user);
    }

    public void setRequestComment(String comment, int requestId){
        RepairRequest request = requestRepository.findById(requestId);
        request.setComment(comment);
        requestRepository.save(request);
    }

    public Page<RepairRequest> findPaginated(Pageable pageable) {
        List<RepairRequest> requests = getAllRequests();
        return getPaginatedRequests(pageable, requests);
    }

    public Page<RepairRequest> findPaginatedByUser(Pageable pageable) {
        List<RepairRequest> requests = getRequestsByUser();
        return getPaginatedRequests(pageable, requests);
    }

    public Page<RepairRequest> findPaginatedByMaster(Pageable pageable) {
        List<RepairRequest> requests = getRequestsByMaster();
        return getPaginatedRequests(pageable, requests);
    }

    private Page<RepairRequest> getPaginatedRequests(Pageable pageable, List<RepairRequest> requests) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<RepairRequest> list;

        if (requests.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, requests.size());
            list = requests.subList(startItem, toIndex);
        }

        return new PageImpl<>(list,
                PageRequest.of(currentPage, pageSize), requests.size());
    }

}
