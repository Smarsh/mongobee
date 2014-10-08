package com.github.mongobee.utils;

import com.github.mongobee.changeset.Changelog;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Comparator;

import static org.springframework.util.StringUtils.hasText;

/**
 * Sort Changelogs by 'order' value or class name (if no 'order' is set)
 *
 * @author lstolowski
 * @since 2014-09-17
 */
public class ChangelogComparator implements Comparator<Class<?>>, Serializable {
  @Override
  public int compare(Class<?> o1, Class<?> o2) {
    Changelog c1 = o1.getAnnotation(Changelog.class);
    Changelog c2 = o2.getAnnotation(Changelog.class);

    String val1 = hasText(c1.order()) ? o1.getCanonicalName() : c1.order();
    String val2 = hasText(c2.order()) ? o2.getCanonicalName() : c2.order();

    if (val1 == null && val2 == null){
      return 0;
    } else if (val1 == null) {
      return -1;
    } else if (val2 == null) {
      return 1;
    }

    return val1.compareTo(val2);
  }
}