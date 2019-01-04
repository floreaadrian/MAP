using System;
using System.Collections.Generic;
using System.Linq;

namespace ToyLanguage.Model
{
    public class MyStack<T> : IStack<T>
    {
        private List<T> stack = new List<T>();
        public MyStack()
        {
        }

        public void clear()
        {
            this.stack.Clear();
        }

        public bool isEmpty()
        {
            return this.stack.Count() == 0;
        }

        public T pop()
        {
            T elem = this.stack[this.stack.Count() - 1];
            this.stack.RemoveAt(this.stack.Count() - 1);
            return elem;
        }
    
        public void push(T v)
        {
            this.stack.Add(v);
        }
        public override string ToString()
        {
            String toStr = "";
            for (int i = this.stack.Count()-1; i >= 0; --i)
            {
                toStr += this.stack[i].ToString() + " ";
            }
            return toStr;
        }
    }
}
