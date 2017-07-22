package com.losek.regression.symbolic.expression

class ExpressionTree {

    boolean isOperator(String c) {
        c in ['+', '-', '*', '/','^']
    }

    String inorder(Node t) {
        t ? """${inorder(t.left)}${t.value}${inorder(t.right)}""" : ""
    }

    Node constructTree(ArrayList<String> postfix) {
        Stack<Node> st = new Stack()
        Node t, t1, t2

        // Traverse through every character of
        // input expression
        for (int i = 0; i < postfix.size(); i++) {

            // If operand, simply push into stack
            if (!isOperator(postfix[i])) {
                t = new Node(postfix[i])
                st.push(t)
            } else // operator
            {
                t = new Node(postfix[i])

                // Pop two top nodes
                // Store top
                t1 = st.pop()      // Remove top
                t2 = st.pop()

                //  make them children
                t.right = t1
                t.left = t2

                // System.out.println(t1 + "" + t2);
                // Add this subexpression to stack
                st.push(t)
            }
        }

        //  only element will be root of expression
        // tree
        t = st.peek()
        st.pop()

        return t
    }

}
