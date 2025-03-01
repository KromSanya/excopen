import { FC } from "react";
import {
    Breadcrumb,
    BreadcrumbItem,
    BreadcrumbLink,
    BreadcrumbList,
    BreadcrumbPage,
    BreadcrumbSeparator
} from "@/shared/ui";
import {BreadcrumbType, useBreadcrumbs} from "@/features/breadcrumbs/model";

export const Index: FC = () => {

    const breadcrumbs: BreadcrumbType[] = useBreadcrumbs()

    return (
        <Breadcrumb>
            <BreadcrumbList>
                {breadcrumbs.map((breadcrumb, index) => (
                    <BreadcrumbItem key={breadcrumb.path}>
                        {index === breadcrumbs.length - 1 ? (
                            <BreadcrumbPage>
                                {breadcrumb.label}
                            </BreadcrumbPage>
                        ) : (
                            <BreadcrumbLink href={breadcrumb.path}>
                                {breadcrumb.label}
                            </BreadcrumbLink>
                        )}
                        {index !== breadcrumbs.length - 1 && <BreadcrumbSeparator />}
                    </BreadcrumbItem>
                ))}
            </BreadcrumbList>
        </Breadcrumb>
    );
};