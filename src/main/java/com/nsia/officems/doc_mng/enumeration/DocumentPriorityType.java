package com.nsia.officems.doc_mng.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentPriorityType {
          Normal(1)
        , Medium(2)
        , Urgent(3);
         private int value;
}
