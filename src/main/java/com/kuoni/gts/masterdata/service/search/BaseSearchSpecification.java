package com.kuoni.gts.masterdata.service.search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.common.base.Optional;
import com.kuoni.gts.masterdata.service.criteria.CriteriaFilterType;
import com.kuoni.gts.masterdata.service.criteria.types.FilterTypes;
import com.kuoni.services.domain.v1_0.Comparison;
import com.kuoni.services.domain.v1_0.WsSearchFilter;

/**
 * <b>Description:</b> The abstract class for search specifications.
 * The methods getSearchFilters and  getFieldPath should be redefined to implement the user specific implementation.
 */
public class BaseSearchSpecification<T> implements Specification<T> {

  private List<WsSearchFilter> searchFilters;

  public BaseSearchSpecification(List<WsSearchFilter> searchFilters) {
    this.searchFilters = searchFilters;
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    query.distinct(true);

    List<Predicate> conjunctions = groupMap().values().stream()
            .map(wsSearchFilters -> generatePerGroupDisjunctions(root, cb, wsSearchFilters))
            .collect(Collectors.toList());

    return cb.and(toArray(conjunctions));
  }

  private Predicate generatePerGroupDisjunctions(Root<T> root, CriteriaBuilder criteriaBuilder,
      List<WsSearchFilter> wsSearchFilters) {
    List<Predicate> disjunctions = wsSearchFilters.stream()
            .map(searchFilter -> buildPredicate(root, criteriaBuilder, searchFilter))
            .collect(Collectors.toList());

    return criteriaBuilder.or(toArray(disjunctions));
  }

  private Predicate buildPredicate(Root<T> root, CriteriaBuilder criteriaBuilder,
                                   WsSearchFilter searchFilter) {
    CriteriaFilterType<Object> filterType = FilterTypes.getFilterType(searchFilter);
    return filterType.createPredicate(getFieldPath(searchFilter, root), searchFilter.getValue(),
        searchFilter.getComparison(), criteriaBuilder);
  }

  private MultiValueMap<String, WsSearchFilter> groupMap() {
    MultiValueMap<String, WsSearchFilter> groupMap = new LinkedMultiValueMap<>();

    if (searchFilters == null) {
      return groupMap;
    }
    for (WsSearchFilter searchFilter : searchFilters) {
      String key = generateGroupKey(searchFilter);
      groupMap.add(key, searchFilter);
    }
    return groupMap;
  }

  private String generateGroupKey(WsSearchFilter searchFilter) {
    String group = searchFilter.getGroup();
    if (group != null) {
      return group;
    }
    Comparison comparison = Optional.fromNullable(searchFilter.getComparison()).or(Comparison.EQUALS);
    return searchFilter.getName() + "_[" + comparison.name() + "]";
  }

  /** Get a path corresponding to the referenced attribute of searchFilter. */
  public Expression<? extends Comparable> getFieldPath(WsSearchFilter searchFilter, Root<T> root) {
    return root.get(searchFilter.getName());
  }

  private static Predicate[] toArray(List<Predicate> predicates) {
    return predicates.toArray(new Predicate[predicates.size()]);
  }
}
