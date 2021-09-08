package com.nsia.officems.doc_mng.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentSecurityLevel {
      Normal(1), Secret(2), TopSecret(3);
      private int value;
}
