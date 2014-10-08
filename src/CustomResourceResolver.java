import com.sun.facelets.impl.DefaultResourceResolver;
import com.sun.facelets.impl.ResourceResolver;
import java.net.URL;

public class CustomResourceResolver extends DefaultResourceResolver implements ResourceResolver
{
    @Override
    public URL resolveUrl(String resource)
    {
        URL resourceUrl = super.resolveUrl(resource);
        if (resourceUrl == null)
        {
            if (resource.startsWith("/"))
            {
                resource = resource.substring(1);
            }
            resourceUrl = Thread.currentThread().getContextClassLoader().getResource(resource);
        }
        return resourceUrl;
    }
}