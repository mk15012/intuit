package com.intuit.response;

import com.intuit.entry.BidEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidResponse extends AbstractResponse {
    private List<BidEntry> data;
}
