package com.example.intuit.response;

import com.example.intuit.entry.ProductEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse extends AbstractResponse {
    private List<ProductEntry> data;
}
