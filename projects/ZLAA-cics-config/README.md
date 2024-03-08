Example Jakarta RESTful Web Services (JAX-RS) CICS application.

**Build**
...

**Deploy**
...

**Invoke**
...
URL
   http(s)://[_host-name_]:[_port_]/[_context-root_]/[_REST-config_]/[_resource-config_]

Method
   GET

Path
   - context-root
      - defined during deployment
   - REST-config
      - "system"
   - resource-config
      - "properties"   - will return the JVM properties of the target CICS Liberty JVM server
      - "stgprot"      - will return the Storage Protection configuration of the target CICS region
