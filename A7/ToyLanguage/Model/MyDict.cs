using System;
using System.Collections.Generic;

namespace ToyLanguage.Model
{
    public class MyDict<Key, Value> : IDict<Key, Value>
    {
        private Dictionary<Key, Value> map = new Dictionary<Key, Value>();
        public MyDict()
        {
        }
        public void add(Key key, Value val){
            this.map.Add(key, val);
        }

        public bool isDefined(Key id){
            return this.map.ContainsKey(id);
        }
        public void update(Key id, Value val){
            this.map[id] = val;
        }
        public Value lookup(Key id){
            return this.map[id];
        }
        public override string ToString()
        {
            String toStr = "";
            foreach(KeyValuePair<Key, Value> entry in this.map)
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
