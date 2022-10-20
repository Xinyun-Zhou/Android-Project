package com.example.mymarketplaceapp.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import com.example.mymarketplaceapp.models.Category;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.List;

public class ItemDaoTest extends TestCase {
    Item testCase1 = new Item(0, "Earbuds", 81, 1, 0, Category.ELECTRONICS, "6.5 x 5.0 x 3.0 cm; BassUp mode to intesify bass in real-time.");
    Item testCase2 = new Item(4, "Sun Hat", 12, 21, 2, Category.FASHION, "White; Certified UV protection; Women use.");

    @Test
    public void testGetCategoryItems(){
        List<Item> electronicItems = ItemDao.getInstance().getCategoryItems(Category.ELECTRONICS);
        assertThat(electronicItems, hasItems(testCase1));
        List<Item> fashionItems = ItemDao.getInstance().getCategoryItems(Category.FASHION);
        assertThat(fashionItems, hasItems(testCase2));
    }
}
