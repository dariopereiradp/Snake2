import random
from scipy.stats import norm
import sys



def truncated_normal(mu,sigma,a,b):
    alpha = (a - 25)/sigma
    beta = (b-25)/sigma

    alpha_cdf = norm.cdf(alpha)
    beta_cdf= norm.cdf(beta)

    u = random.random()

    xi_cdf = alpha_cdf + u*(beta_cdf - alpha_cdf)
    xi = norm.ppf( xi_cdf )
    x = mu + sigma * xi
    return x

print(truncated_normal(int(sys.argv[1]),int(sys.argv[2]),int(sys.argv[3]),int(sys.argv[4])))
