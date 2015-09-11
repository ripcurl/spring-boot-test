package com.kuoni.gts.masterdata.service.search;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.kuoni.gts.masterdata.db.entities.ListEntry_;
import com.kuoni.gts.masterdata.db.entities.ListHeader;
import com.kuoni.gts.masterdata.db.entities.ListHeader_;
import com.kuoni.services.domain.v1_0.WsSearchFilter;

/** ListHeader search specification */
public class ListSearchSpecification extends BaseSearchSpecification<ListHeader> {

  public static final String ENTRY_ID = "entryId";
  public static final String ENTRY_CODE = "entryCode";
  public static final String ENTRY_NAME = "entryName";
  public static final String ENTRY_TYPE = "entryType";

  private static final Map<String, String> MAP_ENTRY_FIELD_NAMES = ImmutableMap.of(
          ENTRY_ID, ListEntry_.id.getName(),
          ENTRY_CODE, ListEntry_.code.getName(),
          ENTRY_NAME, ListEntry_.name.getName(),
          ENTRY_TYPE, ListEntry_.type.getName());

  private static final Set<String> LIST_HEADER_FIELD_NAMES =
      Sets.newHashSet(
          ListHeader_.id.getName(),
          ListHeader_.name.getName(),
          ListHeader_.code.getName(),
          ListHeader_.created.getName(),
          ListHeader_.createdBy.getName(),
          ListHeader_.lastUpdated.getName(),
          ListHeader_.updatedBy.getName(),
          ListHeader_.ownerId.getName(),
          ListHeader_.ownerCode.getName(),
          ListHeader_.ownerName.getName(),
          ListHeader_.ownerType.getName());

  private static final Set<String> ALLOWED_FIELD_NAMES = ImmutableSet.<String>builder()
         .addAll(MAP_ENTRY_FIELD_NAMES.keySet())
         .addAll(LIST_HEADER_FIELD_NAMES)
         .build();

  public ListSearchSpecification(List<WsSearchFilter> searchFilters) {
    super(searchFilters);
    validateFieldNames(searchFilters);
  }

  private void validateFieldNames(List<WsSearchFilter> searchFilters) {
    for (WsSearchFilter filter : searchFilters) {
      validateFilterField(filter.getName());
    }
  }

  private void validateFilterField(String fieldName) {
    if (!ALLOWED_FIELD_NAMES.contains(fieldName)) {
      throw new IllegalArgumentException(
          String.format("Field with name '%s' is not supported. List of supported fields: %s",
              fieldName, ALLOWED_FIELD_NAMES));
    }
  }

  @Override
  public Expression<? extends Comparable> getFieldPath(WsSearchFilter searchFilter,
      Root<ListHeader> root) {
    String fieldName = searchFilter.getName();

    if (MAP_ENTRY_FIELD_NAMES.containsKey(fieldName)) {
      return root.join(ListHeader_.entries).get(MAP_ENTRY_FIELD_NAMES.get(fieldName));
    }

    return root.get(fieldName);
  }
}
