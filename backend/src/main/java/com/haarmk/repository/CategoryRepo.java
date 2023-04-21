/**
 * 
 */
package com.haarmk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haarmk.model.Category;

/**
 * @author HMK05
 *
 */

@Repository
public interface CategoryRepo  extends JpaRepository<Category, Integer>{

}
