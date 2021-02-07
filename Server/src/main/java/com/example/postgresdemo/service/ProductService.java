package com.example.postgresdemo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.model.ProductSeller;
import com.example.postgresdemo.model.Seller;
import com.example.postgresdemo.repository.ProductRepository;

@Service(value = "productService")
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	@PersistenceContext
	private EntityManager em;

	public List<Product> getAll() {
		return (List<Product>) this.productRepo.findAll();
	}

	public Optional<Product> getOne(Integer id) {
		return this.productRepo.findById(id);
	}

	public Product insert(Product product) {
		product.setCreateDate(new Date());
		return this.productRepo.save(product);
	}

	public Product update(Integer id, @RequestBody Product product) {
		if (this.productRepo.existsById(id)) {
			this.productRepo.save(product);
		} else {
			throw new ResourceNotFoundException("No Product with give id found");
		}
		return product;
	}

	public Optional<Product> delete(Integer id) {
		Optional<Product> one;
		if (this.productRepo.existsById(id)) {
			one = this.getOne(id);
			this.productRepo.deleteById(id);
		} else {
			throw new ResourceNotFoundException("No Product with give id found");
		}
		return one;
	}

	public List<Product> searchAll(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize);

		Page<Product> pagedResult = this.productRepo.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Product>();
		}

	}

	private List<ProductSeller> getSeller(Pageable paging) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProductSeller> createQuery = cb.createQuery(ProductSeller.class);
		Root<ProductSeller> root = createQuery.from(ProductSeller.class);
		Join<ProductSeller, Seller> join = root.join("seller", JoinType.INNER);
		Path<Object> path = root.get("price");
		createQuery.orderBy(cb.asc(path));
		CriteriaQuery<ProductSeller> where = createQuery.where(cb.and(cb.greaterThan(root.get("price"), 0)));
		TypedQuery<ProductSeller> typedQuery = em.createQuery(where);
		typedQuery.setFirstResult(paging.getPageNumber() * paging.getPageSize());
		typedQuery.setMaxResults(paging.getPageSize());
		List<ProductSeller> resultList = typedQuery.getResultList();
		return resultList;
	}

}
