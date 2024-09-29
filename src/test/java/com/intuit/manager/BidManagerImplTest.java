//package com.example.intuit.manager;
//
//import com.example.intuit.entities.Bid;
//import com.example.intuit.entry.BidEntry;
//import com.example.intuit.manager.impl.BidManagerImpl;
//import com.example.intuit.repository.BidRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class BidManagerImplTest {
//
//    @Mock
//    private BidRepository bidRepository;
//
//    @InjectMocks
//    private BidManagerImpl bidManager;
//
//    @Test
//    public void testGetBidsForProduct() {
//        BidEntry bid1 = new BidEntry();
//        bid1.setId(1L);
//        bid1.setAmount(100.0);
//        bid1.setUserId(1L);
//        bid1.setProductId(1L);
//
//        BidEntry bid2 = new BidEntry();
//        bid2.setId(2L);
//        bid2.setAmount(200.0);
//        bid2.setUserId(2L);
//        bid2.setProductId(1L);
//
//        when(bidRepository.getBidsForProduct(1L)).thenReturn(Collections.singletonList(convertToEntity(bid1)));
//
//        List<BidEntry> result = bidManager.getBidsForProduct(1L);
//
//        assertEquals(1, result.size());
//        assertEquals(100.0, result.get(0).getAmount());
//        assertEquals(200.0, result.get(1).getAmount());
//    }
//
//    private Bid convertToEntity(BidEntry bidEntry) {
//        Bid bid = new Bid();
//        bid.setAmount(bidEntry.getAmount());
//        bid.setProductId(bidEntry.getProductId());
//        bid.setUserId(bidEntry.getUserId());
//        return bid;
//
//    }
//
//}