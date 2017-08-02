package com.losek.regression.symbolic.expression

class Node {
    String value
    Node left, right

    Node(String item) {
        value = item
        left = right = null
    }
}
