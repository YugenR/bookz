package com.vinai.bookz.common.pagination;

import com.vinai.bookz.exceptions.PaginationException;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.data.domain.Sort.Direction;
import static org.springframework.data.domain.Sort.unsorted;


public class SortableFields {
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_PAGE_DIM = 10;
    private static final Map<SortableEntities, List<String>> entityFields = new HashMap<>();

    static {
        entityFields.put(SortableEntities.USERS, Arrays.asList("name", "surname", "email"));
        entityFields.put(SortableEntities.BOOKS, Arrays.asList("author", "title", "isbn"));
        entityFields.put(SortableEntities.USERBOOKS, Arrays.asList("book.author", "book.title", "book.isbn"));
    }

    /**
     * Given an entity and a list of keys to sort by, returns a Sort object defining the fields and the direction of the sorting operation performed by the JPA query.
     *
     * @param entity Enum member representing the entity to sort
     * @param sort   List of keys to sort by. Each list element follows the pattern "fieldName sortDirection" (e.g. "title asc")
     */
    public static Sort getSorter(SortableEntities entity, List<String> sort) {
        if (sort == null) {
            return unsorted();
        }

        final AtomicReference<Direction> direction = new AtomicReference<Direction>(Direction.ASC);

        return Sort.by(
                sort.stream().map(s -> s.split(" "))
                        .map(arr -> {
                            // There can't be more than 2 strings
                            if (arr.length != 2) {
                                throw new PaginationException.InvalidSortingArgumentsException();
                            }

                            if (entity == SortableEntities.USERBOOKS) {
                                return getStrings(arr);
                            }
                            return arr;
                        })
                        .peek(arr -> {
                            // If you are sorting with an unexpected key
                            if (!entityFields.get(entity).contains(arr[0])) {
                                throw new PaginationException.InvalidSortingArgumentsException();
                            }
                        }).map(strings -> {
                                    if (Objects.equals(strings[1].toUpperCase(), Direction.ASC.toString())) {
                                        direction.set(Direction.ASC);
                                    } else if (Objects.equals(strings[1].toUpperCase(), Direction.DESC.toString())) {
                                        direction.set(Direction.DESC);
                                    } else {
                                        throw new PaginationException.InvalidSortingArgumentsException();
                                    }
                                    return new Sort.Order(direction.get(), strings[0]);
                                }
                        ).toList()
        );

    }

    private static String[] getStrings(String[] arr) {
        List<String> list = new ArrayList<>();
        switch (arr[0]) {
            case "title":
                list.add("book.title");
                break;
            case "author":
                list.add("book.author");
                break;
            case "isbn":
                list.add("book.isbn");
                break;
        }
        list.add(arr[1]);
        return list.toArray(new String[list.size()]);
    }
}

