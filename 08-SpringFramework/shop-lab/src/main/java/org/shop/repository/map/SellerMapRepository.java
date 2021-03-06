package org.shop.repository.map;

import java.util.ArrayList;
import java.util.List;

import org.shop.data.Seller;
import org.shop.repository.SellerRepository;
import org.springframework.stereotype.Repository;

@Repository("sellerRepository")
public final class SellerMapRepository extends AbstractMapRepository<Seller> implements SellerRepository {
    
    /* (non-Javadoc)
     * @see org.shop.repository.SellerRepository#createOrUpdate(org.shop.data.Seller)
     */
    @Override
    public void createOrUpdateSeller(Seller seller) {
    	if(seller.getId() == null || getSellerById(seller.getId())==null){
    		create(seller);
    	} else {
    		update(seller);
    	}
    }

    /* (non-Javadoc)
     * @see org.shop.repository.SellerRepository#getSellers()
     */
    @Override
    public List<Seller> getSellers() {
        return new ArrayList<Seller>(register.values());
    }

    /* (non-Javadoc)
     * @see org.shop.repository.SellerRepository#getSellerById(java.lang.Long)
     */
    @Override
    public Seller getSellerById(Long sellerId) {
        return get(sellerId);
    }
    
    @Override
    public void deleteSellerById(Long sellerId) {
        delete(sellerId);
    }
}
