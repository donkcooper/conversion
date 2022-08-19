package com.aritra.conversion.model;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VersioningPropertiesIntrospector extends JacksonAnnotationIntrospector {
    public String version;

    public VersioningPropertiesIntrospector(String version) {
        this.version = version;
    }

    @Override
    public Version version() {
        return null;
    }

    @Override
    public PropertyName findNameForDeserialization(Annotated a) {
        PropertyName propertyName = findNameFromVersioningProperties(a);
        if (propertyName != null ) {
            return propertyName;
        }
        return super.findNameForDeserialization(a);
    }

    @Override
    public PropertyName findNameForSerialization(Annotated a) {
        PropertyName propertyName = findNameFromVersioningProperties(a);
        if (propertyName != null ) {
            return propertyName;
        }
        return super.findNameForSerialization(a);
    }

    private PropertyName findNameFromVersioningProperties(Annotated a) {
        VersioningProperties annotation = a.getAnnotation(VersioningProperties.class);
        if(annotation == null) {
            return null;
        }
        for(VersioningProperties.Property property : annotation.value()) {
            if(version.equals(property.version())) {
                return new PropertyName(property.value());
            }
        }
        return null;
    }
}
