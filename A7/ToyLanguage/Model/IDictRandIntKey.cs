using System;
using System.Collections.Generic;
using System.IO;

namespace ToyLanguage.Model
{
    public interface IDictRandIntKey<Value>
    {
        int add(Value val);
        bool isDefined(int id);
        void update(int id, Value val);
        void delete(int id);
        Value lookup(int id);
        IEnumerable<Value> values();
        void clear();
    }
}
