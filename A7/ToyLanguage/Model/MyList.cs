using System;
using System.Collections.Generic;
using System.Linq;

namespace ToyLanguage.Model
{
    public class MyList<T> : IList<T>
    {
        private List<T> list = new List<T>();
        public MyList()
        {
        }

        public void add(T var)
        {
            this.list.Add(var);
        }

        public void clear()
        {
            this.list.Clear();
        }

        public T fromIndex(int index)
        {
            return this.list[index];
        }

        public int size()
        {
            return this.list.Count();
        }

        public override string ToString()
        {
            String toStr = "";
            for (int i = 0; i < this.list.Count(); ++i)
            {
                toStr += this.list[i] + "\n";
            }
            return toStr;
        }
    }
}
