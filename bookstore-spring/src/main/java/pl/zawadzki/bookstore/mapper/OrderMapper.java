package pl.zawadzki.bookstore.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zawadzki.bookstore.dto.OrderDto;
import pl.zawadzki.bookstore.model.Book;
import pl.zawadzki.bookstore.model.Order;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "numberOfBooks", expression ="java(mapBooks(order.getBooks()))")
    OrderDto mapOrderToDto(Order order);

    default Integer mapBooks(List<Book> numberOfBooks) {
        return numberOfBooks.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "books", ignore = true)
    Order mapDtoToOrder(OrderDto orderDto);

}
