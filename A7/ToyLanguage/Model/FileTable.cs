using System;
using System.Collections.Generic;
using System.IO;

namespace ToyLanguage.Model
{
    public class FileTable<Value>: IDictRandIntKey <Value>
    {
        private int key = 0;
        private Dictionary<int, Value> map = new Dictionary<int, Value>();
        public FileTable()
        {
        }

        public int add(Value val)
        {
            this.map.Add(key, val);
            key++;
            return key-1;
        }

        public void delete(int id)
        {
            this.map.Remove(id);
        }

        public bool isDefined(int id)
        {
            return this.map.ContainsKey(id);
        }

        public Value lookup(int id)
        {
            return this.map[id];
        }

        public void update(int id, Value val)
        {
            this.map[id] = val;
        }

        public IEnumerable<Value> values()
        {
            return this.map.Values;
        }
        public override string ToString()
        {
            String toStr = "";
            foreach (KeyValuePair<int, Value> entry in this.map)
            {
                toStr += entry.Key + " --> " + entry.Value + "\n";
            }
            return toStr;
        }

        public void clear()
        {
            this.map.Clear();
        }
    }
}
